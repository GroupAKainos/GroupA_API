package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobRole {

    protected int jobid;

    protected String jobName;

    protected String jobResponsibility;

    protected String specification;

    protected String specSummary;

    protected String capabilityName;

    @JsonCreator
    public JobRole(
            @JsonProperty("jobname") String jobname,
            @JsonProperty("jobResponsibility") String jobResponsibility,
            @JsonProperty("capabilityName") String capabilityName
    ) {
        this.setJobName(jobname);
        this.setJobResponsibility(jobResponsibility);
        this.setCapabilityName(capabilityName);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobname") String jobname
    ) {
        this.setJobName(jobname);
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
