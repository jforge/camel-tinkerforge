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

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.atomspace.camel.component.tinkerforge.TinkerforgeConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.BrickletPiezoSpeaker;

import com.tinkerforge.BrickletPiezoSpeaker.BeepFinishedListener;
import com.tinkerforge.BrickletPiezoSpeaker.MorseCodeFinishedListener;;

public class PiezoSpeakerConsumer extends TinkerforgeConsumer<PiezoSpeakerEndpoint, BrickletPiezoSpeaker> implements BeepFinishedListener, MorseCodeFinishedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(PiezoSpeakerConsumer.class);
    
    public PiezoSpeakerConsumer(PiezoSpeakerEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletPiezoSpeaker(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addBeepFinishedListener(this);
            device.addMorseCodeFinishedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("BeepFinishedListener")) device.addBeepFinishedListener(this);
                if(callback.equals("MorseCodeFinishedListener")) device.addMorseCodeFinishedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void beepFinished() {
        LOG.trace("beepFinished()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletPiezoSpeaker.CALLBACK_BEEP_FINISHED);
            
            
            // ADD BODY
            exchange.getIn().setBody("beep_finished");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
	@Override
    public void morseCodeFinished() {
        LOG.trace("morseCodeFinished()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletPiezoSpeaker.CALLBACK_MORSE_CODE_FINISHED);
            
            
            // ADD BODY
            exchange.getIn().setBody("morse_code_finished");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    

}