package ro.amicus.archive.mappers;

import org.mapstruct.Mapper;
import ro.amicus.archive.dtos.CityDTO;
import ro.amicus.archive.entities.City;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDTO cityToCityDTO(City city);

    List<CityDTO> cityListToCityDTOList(List<City> cityList);

    City cityDTOToCity(CityDTO cityDTO);

}
