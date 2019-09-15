package wang.bannong.gk5.sparrow.common.modelmapper.jdk8;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

/**
 * Supports the JDK8 data types  with  ModelMapper
 */
public class Jdk8Module implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.getConfiguration().getConverters().add(0, new FromOptionalConverter());
        modelMapper.getConfiguration().getConverters().add(0, new ToOptionalConverter());
    }
}
