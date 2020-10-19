package com.ruoyi.project.badminton.arena.controller;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
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
import com.ruoyi.project.badminton.arena.domain.Arena;
import com.ruoyi.project.badminton.arena.service.IArenaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 场馆管理Controller
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
@Controller
@RequestMapping("/badminton/arena")
public class ArenaController extends BaseController
{
    private String prefix = "badminton/arena";

    @Autowired
    private IArenaService arenaService;

    @RequiresPermissions("badminton:arena:view")
    @GetMapping()
    public String arena()
    {
        return prefix + "/arena";
    }

    /**
     * 查询场馆管理列表
     */
    @RequiresPermissions("badminton:arena:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Arena arena)
    {
        startPage();
        List<Arena> list = arenaService.selectArenaList(arena);
        return getDataTable(list);
    }

    /**
     * 导出场馆管理列表
     */
    @RequiresPermissions("badminton:arena:export")
    @Log(title = "场馆管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Arena arena)
    {
        List<Arena> list = arenaService.selectArenaList(arena);
        ExcelUtil<Arena> util = new ExcelUtil<Arena>(Arena.class);
        return util.exportExcel(list, "arena");
    }

    /**
     * 新增场馆管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存场馆管理
     */
    @RequiresPermissions("badminton:arena:add")
    @Log(title = "场馆管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Arena arena)
    {
        return toAjax(arenaService.insertArena(arena));
    }

    /**
     * 修改场馆管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Arena arena = arenaService.selectArenaById(id);
        mmap.put("arena", arena);
        return prefix + "/edit";
    }

    /**
     * 修改保存场馆管理
     */
    @RequiresPermissions("badminton:arena:edit")
    @Log(title = "场馆管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Arena arena)
    {
        return toAjax(arenaService.updateArena(arena));
    }

    /**
     * 删除场馆管理
     */
    @RequiresPermissions("badminton:arena:remove")
    @Log(title = "场馆管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(arenaService.deleteArenaByIds(ids));
    }
    
    @PostMapping( "/syncQtt")
    @ResponseBody
    public AjaxResult syncQtt(){
    	arenaService.syncQtt();
    	return AjaxResult.success("完成");
    }
}
