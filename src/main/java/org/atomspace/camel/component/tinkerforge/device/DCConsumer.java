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
import com.tinkerforge.BrickDC;

import com.tinkerforge.BrickDC.UnderVoltageListener;
import com.tinkerforge.BrickDC.EmergencyShutdownListener;
import com.tinkerforge.BrickDC.VelocityReachedListener;
import com.tinkerforge.BrickDC.CurrentVelocityListener;;

public class DCConsumer extends TinkerforgeConsumer<DCEndpoint, BrickDC> implements UnderVoltageListener, EmergencyShutdownListener, VelocityReachedListener, CurrentVelocityListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(DCConsumer.class);
    
    public DCConsumer(DCEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickDC(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addUnderVoltageListener(this);
            device.addEmergencyShutdownListener(this);
            device.addVelocityReachedListener(this);
            device.addCurrentVelocityListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("UnderVoltageListener")) device.addUnderVoltageListener(this);
                if(callback.equals("EmergencyShutdownListener")) device.addEmergencyShutdownListener(this);
                if(callback.equals("VelocityReachedListener")) device.addVelocityReachedListener(this);
                if(callback.equals("CurrentVelocityListener")) device.addCurrentVelocityListener(this);
                
            }
        }
    }
    
    
	@Override
    public void underVoltage(int voltage) {
        LOG.trace("underVoltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickDC.CALLBACK_UNDER_VOLTAGE);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("under_voltage");;
            
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
    public void emergencyShutdown() {
        LOG.trace("emergencyShutdown()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickDC.CALLBACK_EMERGENCY_SHUTDOWN);
            
            
            // ADD BODY
            exchange.getIn().setBody("emergency_shutdown");;
            
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
    public void velocityReached(short velocity) {
        LOG.trace("velocityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickDC.CALLBACK_VELOCITY_REACHED);
            exchange.getIn().setHeader("velocity", velocity);
            
            
            // ADD BODY
            exchange.getIn().setBody("velocity_reached");;
            
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
    public void currentVelocity(short velocity) {
        LOG.trace("currentVelocity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickDC.CALLBACK_CURRENT_VELOCITY);
            exchange.getIn().setHeader("velocity", velocity);
            
            
            // ADD BODY
            exchange.getIn().setBody("current_velocity");;
            
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