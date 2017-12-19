package SpringStateMachine;

import org.springframework.statemachine.*;
import SpringStateMachine.StateMachineConfig.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testRechazadaSoD() throws Exception
    {
        
        StateMachine<States, Events> stateMachine = StateMachineConfig.buildMachine();
        
        stateMachine.start();
        assertEquals("El primer estado debe ser NUEVA", States.NUEVA, stateMachine.getState().getId());
        
        stateMachine.sendEvent(Events.RECHAZADA_SOD);
        assertEquals("Tras evento RECHAZADA_SOD el estado debe ser RECHAZADA_SOD", States.RECHAZADA_SOD, stateMachine.getState().getId());            
    }
    
    public void testRechazadaOwner() throws Exception
    {
        
        StateMachine<States, Events> stateMachine = StateMachineConfig.buildMachine();
        
        stateMachine.start();
        assertEquals("El primer estado debe ser NUEVA", States.NUEVA, stateMachine.getState().getId());
        
        stateMachine.sendEvent(Events.VALIDADA_SOD);
        assertEquals("Tras evento VALIDADA_SOD el estado debe ser PENDIENTE_APROBACION", States.PENDIENTE_APROBACION, stateMachine.getState().getId());            
        
        stateMachine.sendEvent(Events.RECHAZADA_OWNER);
        assertEquals("Tras evento RECHAZADA_OWNER el estado debe ser RECHAZADA_OWNER", States.RECHAZADA_OWNER, stateMachine.getState().getId());                    
    }
    
    public void testRechazadaGU() throws Exception
    {
        
        StateMachine<States, Events> stateMachine = StateMachineConfig.buildMachine();
        
        stateMachine.start();
        assertEquals("El primer estado debe ser NUEVA", States.NUEVA, stateMachine.getState().getId());
        
        stateMachine.sendEvent(Events.VALIDADA_SOD);
        assertEquals("Tras evento VALIDADA_SOD el estado debe ser PENDIENTE_APROBACION", States.PENDIENTE_APROBACION, stateMachine.getState().getId());            
        
        stateMachine.sendEvent(Events.APROBADA_OWNER);
        assertEquals("Tras evento APROBADA_OWNER el estado debe ser APROBADA_OWNER", States.APROBADA_OWNER, stateMachine.getState().getId());                    
        
        stateMachine.sendEvent(Events.RECHAZADA_GU);
        assertEquals("Tras evento RECHAZADA_GU el estado debe ser RECHAZADA_GU", States.RECHAZADA_GU, stateMachine.getState().getId());                    
    }    
    
    public void testCerrada() throws Exception
    {
        
        StateMachine<States, Events> stateMachine = StateMachineConfig.buildMachine();
        
        stateMachine.start();
        assertEquals("El primer estado debe ser NUEVA", States.NUEVA, stateMachine.getState().getId());
        
        stateMachine.sendEvent(Events.VALIDADA_SOD);
        assertEquals("Tras evento VALIDADA_SOD el estado debe ser PENDIENTE_APROBACION", States.PENDIENTE_APROBACION, stateMachine.getState().getId());            
        
        stateMachine.sendEvent(Events.APROBADA_OWNER);
        assertEquals("Tras evento APROBADA_OWNER el estado debe ser APROBADA_OWNER", States.APROBADA_OWNER, stateMachine.getState().getId());                    
        
        stateMachine.sendEvent(Events.APROBADA_GU);
        assertEquals("Tras evento APROBADA_GU el estado debe ser APROBADA_GU", States.APROBADA_GU, stateMachine.getState().getId());                    
        
        stateMachine.sendEvent(Events.MODELO_MODIFICADO);
        assertEquals("Tras evento MODELO_MODIFICADO el estado debe ser CERRADA", States.CERRADA, stateMachine.getState().getId());                    
    }   
}
