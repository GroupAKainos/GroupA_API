package com.kainos.ea.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRole {

    protected int jobid;

    protected String jobName;

    protected String specSummary;

    protected int bandLevelId;

    protected int capabilityId;

    protected String jobResponsibility;

    public UpdateRole(
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
        this.setBandLevelId(bandLevelId);
        this.setCapabilityId(capabilityId);
        this.setJobResponsibility(jobResponsibility);
    }

}
