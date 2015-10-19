package com.taobao.rigel.rap.organization.web.action;

import com.taobao.rigel.rap.common.ActionBase;
import com.taobao.rigel.rap.common.SystemConstant;
import com.taobao.rigel.rap.organization.bo.Corporation;
import com.taobao.rigel.rap.organization.service.OrganizationMgr;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bosn on 15/10/14.
 */
public class TeamAction extends ActionBase {

    private String name;
    private String accountList;
    private long teamListNum;

    public int getPageNum() {
        if (pageNum > 0)
            return pageNum;
        else
            return 1;
    }

    private int pageNum;

    public List<Corporation> getTeamList() {
        return teamList;
    }

    private List<Corporation> teamList;

    public short getAccessType() {
        return accessType;
    }

    public void setAccessType(short accessType) {
        this.accessType = accessType;
    }

    private short accessType;
    private OrganizationMgr organizationMgr;

    public OrganizationMgr getOrganizationMgr() {
        return organizationMgr;
    }

    public void setOrganizationMgr(OrganizationMgr organizationMgr) {
        this.organizationMgr = organizationMgr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountList() {
        return accountList;
    }

    public void setAccountList(String accountList) {
        this.accountList = accountList;
    }


    public String create() {
        Corporation team = new Corporation();
        team.setName(name);
        team.setUserId(getCurUserId());
        team.setAccountList(Arrays.asList(accountList.split(",")));
        team.setDesc(desc);
        team.setAccessType(accessType);
        team.setLogoUrl("");
        int id = organizationMgr.addTeam(team);
        setJson("{\"id\":" + id + "}");
        return SUCCESS;
    }

    public String teams() {
        long userId = getCurUserId();
        teamList = organizationMgr.getCorporationListWithPager(userId, getPageNum(), SystemConstant.DEFAULT_PAGE_SIZE);
        teamListNum = organizationMgr.getCorporationListWithPagerNum(userId);
        return SUCCESS;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getTeamListNum() {
        return teamListNum;
    }

    public long getTeamListPageInTotal() {
        double result =  Math.floor(teamListNum / SystemConstant.DEFAULT_PAGE_SIZE + 1);
        return (long) result;
    }

    public String manage() {
        return SUCCESS;
    }
}