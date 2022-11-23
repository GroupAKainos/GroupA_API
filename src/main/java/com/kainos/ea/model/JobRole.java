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

    protected String capabilityName;

    @JsonCreator
    public JobRole(
            @JsonProperty("jobname") String jobname,
            @JsonProperty("jobResponsibility") String jobResponsibility
    ) {
        this.setJobName(jobname);
        this.setJobResponsibility(jobResponsibility);
    }

    @JsonCreator
    public JobRole(
            @JsonProperty("jobid") int jobid,
            @JsonProperty("jobname") String jobname,
            @JsonProperty("capabilityName") String capabilityID
    ) {
        this.setJobid(jobid);
        this.setJobName(jobname);
        this.setCapabilityName(capabilityID);
    }

}
