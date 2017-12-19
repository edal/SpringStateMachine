package SpringStateMachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.*;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.*;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;



import java.util.logging.Logger;
import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {
    public static final Logger LOGGER = Logger.getLogger(StateMachineConfig.class.getName());

    public static enum States {
        NUEVA, PENDIENTE_APROBACION, RECHAZADA_SOD, APROBADA_OWNER, RECHAZADA_OWNER, APROBADA_GU, RECHAZADA_GU, CERRADA; 
    }
    
    public static enum Events {
        VALIDADA_SOD, RECHAZADA_SOD, APROBADA_OWNER, RECHAZADA_OWNER, APROBADA_GU, RECHAZADA_GU, MODELO_MODIFICADO;
    }
    


    public static StateMachine<States, Events> buildMachine() throws Exception {
        Builder<States, Events> builder = StateMachineBuilder.builder();
    
        builder.configureStates()
            .withStates()
                .initial(States.NUEVA)
                .states(EnumSet.allOf(States.class));
    
        builder.configureTransitions()
            .withExternal()
                .source(States.NUEVA).target(States.PENDIENTE_APROBACION)
                .event(Events.VALIDADA_SOD)
                .and()
            .withExternal()
                .source(States.NUEVA).target(States.RECHAZADA_SOD)
                .event(Events.RECHAZADA_SOD)
                .and()                
            .withExternal()
                .source(States.PENDIENTE_APROBACION).target(States.APROBADA_OWNER)
                .event(Events.APROBADA_OWNER)
                .and()                
            .withExternal()
                .source(States.PENDIENTE_APROBACION).target(States.RECHAZADA_OWNER)
                .event(Events.RECHAZADA_OWNER)                
                .and()                
             .withExternal()
                .source(States.APROBADA_OWNER).target(States.APROBADA_GU)
                .event(Events.APROBADA_GU)
                .and()                
             .withExternal()
                .source(States.APROBADA_OWNER).target(States.RECHAZADA_GU)
                .event(Events.RECHAZADA_GU)            
                .and()                
             .withExternal()
                .source(States.APROBADA_GU).target(States.CERRADA)
                .event(Events.MODELO_MODIFICADO);
                
    
        return builder.build();
    }
}
