package com.ifpe.sistema_ponto_eletronico.convert;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConvert {
    
    private final ModelMapper modelMapper;

    public ModelMapperConvert(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    public <O, D> D convertObject(O origin, Class<D> destination){
        return modelMapper.map(origin, destination);
    }

    public <O, D> List<D> convertListObject(List<O> origin, Class<D> destination){
        List<D> destinationObj = new ArrayList<>();
        for (O o : origin) {
            destinationObj.add(modelMapper.map(o, destination));
        }

        return destinationObj;
    }
}
