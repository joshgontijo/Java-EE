/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.batch.flow;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class CustomPartitionMapper implements PartitionMapper {

    private static final Logger logger = Logger.getLogger(CustomPartitionMapper.class.getName());
    private final int partitionNum = 2;

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        logger.log(Level.INFO, "---- RAND PARTITION = {0} ----", partitionNum);

        return new PartitionPlanImpl() {
            @Override
            public int getPartitions() {
                return partitionNum;
            }

            @Override
            public int getThreads() {
                return partitionNum;
            }

            @Override
            public Properties[] getPartitionProperties() {
                Properties[] props = new Properties[getPartitions()];
                for (int i = 0; i < getPartitions(); i++) {
                    props[i] = new Properties();
                    props[i].setProperty("start", String.valueOf(i * 10 + 1));
                    props[i].setProperty("end", String.valueOf((i + 1) * 10));
                }
                return props;
            }

        };
    }
}
