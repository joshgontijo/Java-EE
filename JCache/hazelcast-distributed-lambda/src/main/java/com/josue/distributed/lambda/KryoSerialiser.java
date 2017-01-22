package com.josue.distributed.lambda;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * Created by Josue on 22/07/2016.
 */
public class KryoSerialiser implements StreamSerializer<Object> {

    private final Kryo kryo = new Kryo();

    public KryoSerialiser() {
//        kryo.register(Runnable.class);
        kryo.register(Callable.class);
    }

    public int getTypeId() {
        return 5;
    }

    public void write(ObjectDataOutput out, Object object)
            throws IOException {
        Output output = new Output((OutputStream) out);
        kryo.writeClassAndObject(output, object);
        output.flush();
    }

    public Object read(ObjectDataInput in) throws IOException {
        Input input = new Input((InputStream) in);
        return kryo.readClassAndObject(input);
    }

    public void destroy() {
    }
}