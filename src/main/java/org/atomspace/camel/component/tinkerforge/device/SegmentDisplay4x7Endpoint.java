/**
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.atomspace.camel.component.tinkerforge.device;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletSegmentDisplay4x7;

public class SegmentDisplay4x7Endpoint extends TinkerforgeEndpoint<SegmentDisplay4x7Consumer, SegmentDisplay4x7Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(SegmentDisplay4x7Endpoint.class);
    
    private short[] segments;
    private Short brightness;
    private Boolean colon;
    private Short valueFrom;
    private Short valueTo;
    private Short increment;
    private Long length;

        
    public SegmentDisplay4x7Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new SegmentDisplay4x7Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new SegmentDisplay4x7Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletSegmentDisplay4x7 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletSegmentDisplay4x7 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setSegments":
                device.setSegments(
                        getValue(short[].class, "segments", m, getSegments()),
                        getValue(short.class, "brightness", m, getBrightness()),
                        getValue(boolean.class, "colon", m, getColon())
                    );
                break;

            case "getSegments":
                response = device.getSegments();
                break;

            case "startCounter":
                device.startCounter(
                        getValue(short.class, "valueFrom", m, getValueFrom()),
                        getValue(short.class, "valueTo", m, getValueTo()),
                        getValue(short.class, "increment", m, getIncrement()),
                        getValue(long.class, "length", m, getLength())
                    );
                break;

            case "getCounterValue":
                response = device.getCounterValue();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public short[] getSegments(){
        return segments;
    }

    public void setSegments(short[] segments){
        this.segments = segments;
    }

    public Short getBrightness(){
        return brightness;
    }

    public void setBrightness(Short brightness){
        this.brightness = brightness;
    }

    public Boolean getColon(){
        return colon;
    }

    public void setColon(Boolean colon){
        this.colon = colon;
    }

    public Short getValueFrom(){
        return valueFrom;
    }

    public void setValueFrom(Short valueFrom){
        this.valueFrom = valueFrom;
    }

    public Short getValueTo(){
        return valueTo;
    }

    public void setValueTo(Short valueTo){
        this.valueTo = valueTo;
    }

    public Short getIncrement(){
        return increment;
    }

    public void setIncrement(Short increment){
        this.increment = increment;
    }

    public Long getLength(){
        return length;
    }

    public void setLength(Long length){
        this.length = length;
    }



}
