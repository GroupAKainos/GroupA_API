package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobRole {

    protected int jobid;

    protected String jobName;

    protected String jobResponsibility;

    protected String specification;

    protected String specSummary;

    protected int capabilityID;

    protected String capabilityName;

    protected int bandLevelID;

    protected String bandLevelName;

    protected int jobFamilyID;

    protected int listID;

    protected String listName;

    protected String name;

    protected List<JobRole> object;

    public JobRole() {
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobid") int jobid,
            @JsonProperty("jobResponsibility") String jobResponsibility,
            @JsonProperty("specification") String specification,
            @JsonProperty("specSummary") String specSummary,
            @JsonProperty("capabilityName") String capabilityName,
            @JsonProperty("bandLevelId") int bandlevelID,
            @JsonProperty("bandLevelName") String bandlevelname
    ) {
        this.setJobid(jobid);
        this.setJobResponsibility(jobResponsibility);
        this.setSpecification(specification);
        this.setSpecSummary(specSummary);
        this.setCapabilityName(capabilityName);
        this.setBandLevelID(bandlevelID);
        this.setBandLevelName(bandlevelname);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobResponsibility") String jobResponsibility,
            @JsonProperty("specification") String specification,
            @JsonProperty("specSummary") String specSummary,
            @JsonProperty("capabilityName") String capabilityName,
            @JsonProperty("bandLevelId") int bandlevelID,
            @JsonProperty("bandLevelName") String bandlevelname
    ) {
        this.setJobResponsibility(jobResponsibility);
        this.setSpecification(specification);
        this.setSpecSummary(specSummary);
        this.setCapabilityName(capabilityName);
        this.setBandLevelID(bandlevelID);
        this.setBandLevelName(bandlevelname);

    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobname") String jobname
    ) {
        this.setJobName(jobname);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobid") int jobid
    ) {
        this.setJobid(jobid);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("listID") int listID,
            @JsonProperty("listName") String listName
    ) {
     this.setListID(listID);
     this.setListName(listName);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobname") String jobname,
            @JsonProperty("capabilityName") String capabilityName
    ) {
        this.setJobName(jobname);
        this.setCapabilityName(capabilityName);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobName") String jobName,
            @JsonProperty("jobResponsibility") String jobResponsibilty,
            @JsonProperty("specSummary") String spec,
            @JsonProperty("bandLevelId") int bandLevelID,
            @JsonProperty("jobFamilyId") int jobFamilyID,
            @JsonProperty("capabilityId") int capabilityID
    ) {
        this.setJobName(jobName);
        this.setJobResponsibility(jobResponsibilty);
        this.setSpecSummary(spec);
        this.setBandLevelID(bandLevelID);
        this.setJobFamilyID(jobFamilyID);
        this.setCapabilityID(capabilityID);
    }

    public JobRole(
            @JsonProperty("jobid") int jobid,
            @JsonProperty("jobName") String jobName,
            @JsonProperty("specSummary") String specSummary,
            @JsonProperty("bandLevelId") int bandLevelId,
            @JsonProperty("capabilityId") int capabilityId,
            @JsonProperty("jobResponsibility") String jobResponsibility
    ){
        this.setJobid(jobid);
        this.setJobName(jobName);
        this.setSpecSummary(specSummary);
        this.setBandLevelID(bandLevelId);
        this.setCapabilityID(capabilityId);
        this.setJobResponsibility(jobResponsibility);
    }


}