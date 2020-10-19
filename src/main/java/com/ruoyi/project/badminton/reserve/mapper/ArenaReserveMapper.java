package com.ruoyi.project.badminton.reserve.mapper;

import java.util.List;
import com.ruoyi.project.badminton.reserve.domain.ArenaReserve;

/**
 * 场馆预定Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
public interface ArenaReserveMapper 
{
    /**
     * 查询场馆预定
     * 
     * @param id 场馆预定ID
     * @return 场馆预定
     */
    public ArenaReserve selectArenaReserveById(Long id);

    /**
     * 查询场馆预定列表
     * 
     * @param arenaReserve 场馆预定
     * @return 场馆预定集合
     */
    public List<ArenaReserve> selectArenaReserveList(ArenaReserve arenaReserve);

    /**
     * 新增场馆预定
     * 
     * @param arenaReserve 场馆预定
     * @return 结果
     */
    public int insertArenaReserve(ArenaReserve arenaReserve);

    /**
     * 修改场馆预定
     * 
     * @param arenaReserve 场馆预定
     * @return 结果
     */
    public int updateArenaReserve(ArenaReserve arenaReserve);

    /**
     * 删除场馆预定
     * 
     * @param id 场馆预定ID
     * @return 结果
     */
    public int deleteArenaReserveById(Long id);

    /**
     * 批量删除场馆预定
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArenaReserveByIds(String[] ids);
}
