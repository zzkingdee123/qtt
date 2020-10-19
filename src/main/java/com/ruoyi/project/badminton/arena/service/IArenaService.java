package com.ruoyi.project.badminton.arena.service;

import java.util.List;
import com.ruoyi.project.badminton.arena.domain.Arena;

/**
 * 场馆管理Service接口
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
public interface IArenaService 
{
    /**
     * 查询场馆管理
     * 
     * @param id 场馆管理ID
     * @return 场馆管理
     */
    public Arena selectArenaById(Long id);

    /**
     * 查询场馆管理列表
     * 
     * @param arena 场馆管理
     * @return 场馆管理集合
     */
    public List<Arena> selectArenaList(Arena arena);

    /**
     * 新增场馆管理
     * 
     * @param arena 场馆管理
     * @return 结果
     */
    public int insertArena(Arena arena);

    /**
     * 修改场馆管理
     * 
     * @param arena 场馆管理
     * @return 结果
     */
    public int updateArena(Arena arena);

    /**
     * 批量删除场馆管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArenaByIds(String ids);

    /**
     * 删除场馆管理信息
     * 
     * @param id 场馆管理ID
     * @return 结果
     */
    public int deleteArenaById(Long id);

	public void syncQtt();
}
