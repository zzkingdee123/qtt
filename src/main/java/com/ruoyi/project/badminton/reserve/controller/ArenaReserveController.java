package com.ruoyi.project.badminton.reserve.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.badminton.reserve.domain.ArenaReserve;
import com.ruoyi.project.badminton.reserve.service.IArenaReserveService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 场馆预定Controller
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
@Controller
@RequestMapping("/badminton/reserve")
public class ArenaReserveController extends BaseController
{
    private String prefix = "badminton/reserve";

    @Autowired
    private IArenaReserveService arenaReserveService;

    @RequiresPermissions("badminton:reserve:view")
    @GetMapping()
    public String reserve()
    {
        return prefix + "/reserve";
    }

    /**
     * 查询场馆预定列表
     */
    @RequiresPermissions("badminton:reserve:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArenaReserve arenaReserve)
    {
        startPage();
        List<ArenaReserve> list = arenaReserveService.selectArenaReserveList(arenaReserve);
        return getDataTable(list);
    }

    /**
     * 导出场馆预定列表
     */
    @RequiresPermissions("badminton:reserve:export")
    @Log(title = "场馆预定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ArenaReserve arenaReserve)
    {
        List<ArenaReserve> list = arenaReserveService.selectArenaReserveList(arenaReserve);
        ExcelUtil<ArenaReserve> util = new ExcelUtil<ArenaReserve>(ArenaReserve.class);
        return util.exportExcel(list, "reserve");
    }

    /**
     * 新增场馆预定
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存场馆预定
     */
    @RequiresPermissions("badminton:reserve:add")
    @Log(title = "场馆预定", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArenaReserve arenaReserve)
    {
        return toAjax(arenaReserveService.insertArenaReserve(arenaReserve));
    }

    /**
     * 修改场馆预定
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ArenaReserve arenaReserve = arenaReserveService.selectArenaReserveById(id);
        mmap.put("arenaReserve", arenaReserve);
        return prefix + "/edit";
    }

    /**
     * 修改保存场馆预定
     */
    @RequiresPermissions("badminton:reserve:edit")
    @Log(title = "场馆预定", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArenaReserve arenaReserve)
    {
        return toAjax(arenaReserveService.updateArenaReserve(arenaReserve));
    }

    /**
     * 删除场馆预定
     */
    @RequiresPermissions("badminton:reserve:remove")
    @Log(title = "场馆预定", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(arenaReserveService.deleteArenaReserveByIds(ids));
    }
}
