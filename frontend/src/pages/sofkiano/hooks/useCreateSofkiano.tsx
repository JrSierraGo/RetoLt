import { useEffect, useState } from 'react'
import DocumentTypeService from '../../../services/api/DocumentTypeService';
import CustomerService from '../../../services/api/CustomerService';
import CountryService from '../../../services/api/CountryService';
import StateService from '../../../services/api/StateService';
import CityService from '../../../services/api/CityService';
import { useLocation, useNavigate } from 'react-router-dom';
import SofkianoService from '../../../services/api/SofkianoService';
import LocationService from '../../../services/api/LocationService';
import { Customer } from '../../../types/Customer';
import { DocumentType } from "../../../types/DocumentType";
import { Country } from "../../../types/Country";
import { State } from "../../../types/State";
import { City } from "../../../types/City";
import { Form, message } from 'antd';

export const useCreateSofkiano = () => {
  
    const [form] = Form.useForm()
    const {state} = useLocation();
    const navigate = useNavigate();
    const {sofkiano} = state;

    const [doumentTypeList, setDocumentTypeList] = useState<DocumentType[]>([])
    const [customerList, setCustomerList] = useState<Customer[]>([])
    const [countryList, setCountryList] = useState<Country[]>([])
    const [stateList, setStateList] = useState<State[]>([])
    const [cityList, setCityList] = useState<City[]>([])
    const [sofkianoEditing, setSofkianoEditing] = useState(sofkiano)
    const [sofkianoToSave, setSofkianoToSave] = useState({})

    useEffect(() => {
      getDocumentTypes();
      getCustomers();
      getCountries();
    }, []);

    useEffect(() => {
      if(sofkianoEditing && sofkianoEditing.id){
        getLocationBySofkiano(sofkianoEditing.id);
        getStatesByCountryId(sofkianoEditing.countryId);;
        getCityByStateId(sofkianoEditing.stateId);
        form.setFieldsValue(sofkianoEditing);
      }
    
    }, [form, sofkianoEditing?.locationId]);

    const getDocumentTypes = async () => {
      await DocumentTypeService.getAll()
      .then((response:DocumentType[]) => {
        if(response && Array.isArray(response)){
          setDocumentTypeList(response)
        }
      })
      .catch(error => {
        console.error(error)
      });
    }

    const getCustomers = async () => {
      await CustomerService.getAll()
      .then((response:Customer[]) => {
        if(response && Array.isArray(response)){
          setCustomerList(response)
        }
      })
      .catch(error => console.error(error));
    }

    const getCountries = async () => {
      await CountryService.getAll()
      .then((response:Country[]) => {
        if(response && Array.isArray(response)){
          setCountryList(response)
        }
      })
      .catch(error => console.error(error));
    }

    const getStatesByCountryId = async (countryId:string) => {
      setCityList([])
      setStateList([])
       await StateService.getStateListByCountry(countryId)
      .then((response:State[]) => {
        if(response && Array.isArray(response)){
          setStateList(response)
        }
      })
      .catch(error => console.error(error));
    }

    const getCityByStateId = (stateId:string) => {
      setCityList([])
      CityService.getCityListByState(stateId)
      .then((response:City[]) => {
        if(response && Array.isArray(response)){
          setCityList(response)
        }
      })
      .catch(error => console.error(error));
    }

    const saveSofkiano = async () => {
      await SofkianoService.save(sofkianoToSave)
      .then(response => {
        if(response){
          message.success("Guardado correctamente")
          form.resetFields()
          navigate("/sofkianos")
        }else{
          message.error("Error guardando")
        }
      })
      .catch(error => message.error("Error guardando"));
    }

    const getLocationBySofkiano = async (sofkianoId:string) => {
      await LocationService.getBySofkianoId(sofkianoId)
      .then(response => {
        if (response) {
          setSofkianoEditing((sofkiano:any) => ({...sofkiano, 
            locationId: response.id,
            cityId: response.cityId,
            address: response.address,
            neighborhood: response.neighborhood,
            additionalIndications: response.additionalIndications,
            countryId: response.countryId,
            stateId: response.stateId
          }))
        }
      })
      .catch(error => console.error(error));
    }


  return {
    doumentTypeList,
    customerList,
    countryList,
    stateList,
    getStatesByCountryId,
    cityList,
    getCityByStateId,
    sofkianoEditing,
    setSofkianoToSave,
    saveSofkiano,
    form
  }
}