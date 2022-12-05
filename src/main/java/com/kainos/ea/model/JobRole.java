package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRole {

    protected int jobid;

    protected String jobName;

    protected String jobResponsibility;

    protected String specification;

    protected String specSummary;

    protected String capabilityName;

    protected int bandLevelID;

    protected String bandLevelName;

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
            @JsonProperty("jobname") String jobname,
            @JsonProperty("capabilityName") String capabilityName
    ) {
        this.setJobName(jobname);
        this.setCapabilityName(capabilityName);
    }
}