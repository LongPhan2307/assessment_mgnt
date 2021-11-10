package uit.thesis.assessment_mgnt.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MapDtoToModel<E extends Object, T extends Object> {
    public T map(E dto, T model) {
        Method[] dtoMethods = dto.getClass().getMethods();
        List<String> dtoGetters = new LinkedList<String>();

        // extract all getters from dto methods
        for(Method method : dtoMethods) {
            if(!method.getName().equals("getClass") && method.getName().startsWith("get"))
                dtoGetters.add(method.getName());
        }

        // map dto properties to model properties
        for(String getter : dtoGetters) {

            try {
                // get dto properties value
                Object dtoValue = dto.getClass().getMethod(getter).invoke(dto);
                // parse dto getter to model setter
                String modelSetter = getter.replaceFirst("get", "set");

                // get properties type
                Class<?> propertyType = model.getClass().getMethod(modelSetter, dtoValue.getClass()).getParameterTypes()[0];

                // call model's setter to set dtoValue to model
                model.getClass().getMethod(modelSetter, propertyType).invoke(model, propertyType.cast(dtoValue));

            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }

        return model;
    }
}

