package com.ruoyi.project.badminton.arena.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.badminton.arena.mapper.ArenaMapper;
import com.ruoyi.project.badminton.arena.domain.Arena;
import com.ruoyi.project.badminton.arena.service.IArenaService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.text.Convert;

/**
 * 场馆管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
@Service
public class ArenaServiceImpl implements IArenaService 
{
    @Autowired
    private ArenaMapper arenaMapper;

    /**
     * 查询场馆管理
     * 
     * @param id 场馆管理ID
     * @return 场馆管理
     */
    @Override
    public Arena selectArenaById(Long id)
    {
        return arenaMapper.selectArenaById(id);
    }

    /**
     * 查询场馆管理列表
     * 
     * @param arena 场馆管理
     * @return 场馆管理
     */
    @Override
    public List<Arena> selectArenaList(Arena arena)
    {
        return arenaMapper.selectArenaList(arena);
    }

    /**
     * 新增场馆管理
     * 
     * @param arena 场馆管理
     * @return 结果
     */
    @Override
    public int insertArena(Arena arena)
    {
        return arenaMapper.insertArena(arena);
    }

    /**
     * 修改场馆管理
     * 
     * @param arena 场馆管理
     * @return 结果
     */
    @Override
    public int updateArena(Arena arena)
    {
        return arenaMapper.updateArena(arena);
    }

    /**
     * 删除场馆管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArenaByIds(String ids)
    {
        return arenaMapper.deleteArenaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除场馆管理信息
     * 
     * @param id 场馆管理ID
     * @return 结果
     */
    @Override
    public int deleteArenaById(Long id)
    {
        return arenaMapper.deleteArenaById(id);
    }

    /**
     * 同步群体通
     */
	@Override
	public void syncQtt() {
		
		int count = getArenaCount();//总共多少页
		
		Map<String,String> map = null;
		for(int i=0;i<count;i++){
			map = new HashMap<String,String>();
			map.put("citys", "440100");
			map.put("city", "440100");
			map.put("pageIndex", String.valueOf(i+1));
			map.put("pageSize", "10");
			map.put("sort", "6");
			map.put("tag", "羽毛球");
			
			JSONArray arenaInfo = com.ruoyi.project.badminton.QttAPI.getArenaInfo(map);
			
			prase(arenaInfo);//解析入库
		}
		
	}
	
	private void prase(JSONArray arenaInfo) {
		JSONArray arr = arenaInfo.getJSONObject(0).getJSONArray("data");
		JSONObject jsonObject = null;
		Arena arena  = null;
		List<Arena> selectArenaList= null;
		for (int i=0;i<arr.size();i++) {
			 arena = new Arena();
			 jsonObject = arr.getJSONObject(i);
			 arena.setNumber(jsonObject.getString("code"));
			 arena.setName(jsonObject.getString("name"));
			 
			 selectArenaList = arenaMapper.selectArenaList(arena);
			 if(selectArenaList==null || selectArenaList.size()==0)
			 arenaMapper.insertArena(arena);
		}
		
	}

	private int getArenaCount(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("citys", "440100");
		map.put("city", "440100");
		map.put("pageIndex", "1");
		map.put("pageSize", "10");
		map.put("sort", "6");
		map.put("tag", "羽毛球");
		
		Integer arenaCount = com.ruoyi.project.badminton.QttAPI.getArenaCount(map);
		int tmp = arenaCount%10;
		int tmp1 = arenaCount/10;
		int count = tmp==0?tmp1:tmp1+1;
		return count;
	}
}
