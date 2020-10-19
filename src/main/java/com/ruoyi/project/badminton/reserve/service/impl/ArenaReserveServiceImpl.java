package com.ruoyi.project.badminton.reserve.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.badminton.reserve.mapper.ArenaReserveMapper;
import com.ruoyi.project.badminton.reserve.domain.ArenaReserve;
import com.ruoyi.project.badminton.reserve.service.IArenaReserveService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 场馆预定Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
@Service
public class ArenaReserveServiceImpl implements IArenaReserveService 
{
    @Autowired
    private ArenaReserveMapper arenaReserveMapper;

    /**
     * 查询场馆预定
     * 
     * @param id 场馆预定ID
     * @return 场馆预定
     */
    @Override
    public ArenaReserve selectArenaReserveById(Long id)
    {
        return arenaReserveMapper.selectArenaReserveById(id);
    }

    /**
     * 查询场馆预定列表
     * 
     * @param arenaReserve 场馆预定
     * @return 场馆预定
     */
    @Override
    public List<ArenaReserve> selectArenaReserveList(ArenaReserve arenaReserve)
    {
        return arenaReserveMapper.selectArenaReserveList(arenaReserve);
    }

    /**
     * 新增场馆预定
     * 
     * @param arenaReserve 场馆预定
     * @return 结果
     */
    @Override
    public int insertArenaReserve(ArenaReserve arenaReserve)
    {
        return arenaReserveMapper.insertArenaReserve(arenaReserve);
    }

    /**
     * 修改场馆预定
     * 
     * @param arenaReserve 场馆预定
     * @return 结果
     */
    @Override
    public int updateArenaReserve(ArenaReserve arenaReserve)
    {
        return arenaReserveMapper.updateArenaReserve(arenaReserve);
    }

    /**
     * 删除场馆预定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArenaReserveByIds(String ids)
    {
        return arenaReserveMapper.deleteArenaReserveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除场馆预定信息
     * 
     * @param id 场馆预定ID
     * @return 结果
     */
    @Override
    public int deleteArenaReserveById(Long id)
    {
        return arenaReserveMapper.deleteArenaReserveById(id);
    }
}
