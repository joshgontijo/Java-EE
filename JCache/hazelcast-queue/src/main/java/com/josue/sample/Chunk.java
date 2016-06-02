package com.josue.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Josue on 02/06/2016.
 */
@Entity(name = "chunk")
public class Chunk implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private final Integer sequence;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "server_id")
    private String serverId;

    @Column(name = "thread_id")
    private String threadId;

    public Chunk() {
        sequence = 0;
    }

    public Chunk(int sequence, Status status) {
        this.sequence = sequence;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chunk chunk = (Chunk) o;

        if (id != null ? !id.equals(chunk.id) : chunk.id != null) return false;
        if (sequence != null ? !sequence.equals(chunk.sequence) : chunk.sequence != null) return false;
        if (status != chunk.status) return false;
        if (serverId != null ? !serverId.equals(chunk.serverId) : chunk.serverId != null) return false;
        return !(threadId != null ? !threadId.equals(chunk.threadId) : chunk.threadId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (serverId != null ? serverId.hashCode() : 0);
        result = 31 * result + (threadId != null ? threadId.hashCode() : 0);
        return result;
    }
}
