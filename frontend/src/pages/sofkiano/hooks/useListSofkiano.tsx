import { useEffect, useState } from 'react'
import DocumentTypeService from '../../../services/api/DocumentTypeService';
import CustomerService from '../../../services/api/CustomerService';
import CountryService from '../../../services/api/CountryService';
import StateService from '../../../services/api/StateService';
import cityService from '../../../services/api/CityService';
import CityService from '../../../services/api/CityService';
import { useLocation } from 'react-router-dom';

export const useListSofkiano = () => {

    const [doumentTypeList, setDocumentTypeList] = useState([])
    const [customerList, setCustomerList] = useState([])
    const [countryList, setCountryList] = useState([])
    const [stateList, setStateList] = useState([])
    const [cityList, setCityList] = useState([])
    const [sofkianoEditing, setsofkianoEditing] = useState({})

    const {state} = useLocation();
    const {sofkiano} = state;

    useEffect(() => {
      console.log(sofkiano)

      setsofkianoEditing(sofkiano)

      getDocumentTypes();
      getCustomers();
      getCountries()

    
    }, []);

    const getDocumentTypes = () => {
      DocumentTypeService.getAll()
      .then(response => {
        setDocumentTypeList(response)
      })
      .catch(error => console.log(error));
    }

    const getCustomers = () => {
      CustomerService.getAll()
      .then(response => {
        setCustomerList(response)
      })
      .catch(error => console.log(error));
    }

    const getCountries = () => {
      CountryService.getAll()
      .then(response => {
        setCountryList(response)
      })
      .catch(error => console.log(error));
    }

    const getStatesByCountryId = (countryId:string) => {
      setCityList([])
      setStateList([])
      StateService.getStateListByCountry(countryId)
      .then(response => {
        setStateList(response)
      })
      .catch(error => console.log(error));
    }

    const getCityByStateId = (stateId:string) => {
      setCityList([])
      CityService.getCityListByState(stateId)
      .then(response => {
        setCityList(response)
      })
      .catch(error => console.log(error));
    }


  return {
    doumentTypeList,
    customerList,
    countryList,
    stateList,
    getStatesByCountryId,
    cityList,
    getCityByStateId,
    sofkianoEditing
  }
}