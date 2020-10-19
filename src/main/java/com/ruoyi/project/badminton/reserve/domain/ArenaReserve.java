package com.ruoyi.project.badminton.reserve.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 场馆预定对象 arena_reserve
 * 
 * @author ruoyi
 * @date 2020-09-26
 */
public class ArenaReserve extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 场馆编号 */
    @Excel(name = "场馆编号")
    private String arenaNo;

    /** 期间 */
    @Excel(name = "期间")
    private String period;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String starttime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setArenaNo(String arenaNo)
    {
        this.arenaNo = arenaNo;
    }

    public String getArenaNo()
    {
        return arenaNo;
    }
    public void setPeriod(String period)
    {
        this.period = period;
    }

    public String getPeriod()
    {
        return period;
    }
    public void setStarttime(String starttime)
    {
        this.starttime = starttime;
    }

    public String getStarttime()
    {
        return starttime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("arenaNo", getArenaNo())
            .append("period", getPeriod())
            .append("starttime", getStarttime())
            .toString();
    }
}
