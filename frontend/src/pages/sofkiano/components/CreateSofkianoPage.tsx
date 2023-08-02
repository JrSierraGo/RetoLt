import { Button, DatePicker, Divider, Form, Input, Row, Select } from "antd";
import { Fragment } from "react";
import { useCreateSofkiano } from "../hooks/useCreateSofkiano";
import { DocumentType } from "../../../types/DocumentType";
import { Customer } from "../../../types/Customer";
import { Country } from "../../../types/Country";
import { State } from "../../../types/State";
import { City } from "../../../types/City";
import { Sofkiano } from "../../../types/Sofkiano";
import moment from "moment";
import { SofkianoForm } from "../../../types/SofkianoForm";
import 'dayjs/locale/es'
import dayjs from "dayjs";

const { Option } = Select;
const {TextArea} = Input

dayjs.locale('es');

export const CreateSofkianoPage = () => {

    const {
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
      } = useCreateSofkiano();

    const dateToEpoch = (date:Date): number => {
        return moment(date).valueOf();
    }  

    const onFinishForm = (): void => {
        saveSofkiano();
    }

    const onFieldsChange = (_: any, sofkianoForm: SofkianoForm): void => {
        let sofkianoToSave: Sofkiano = {
            id: sofkianoEditing?.id,
            name: sofkianoForm.name,
            lastName: sofkianoForm.lastName,
            documentTypeId: sofkianoForm.documentTypeId,
            documentNumber: sofkianoForm.documentNumber,
            customerId: sofkianoForm.customerId,
            entryDate: dateToEpoch(sofkianoForm.entryDate),
            location:{
                id: sofkianoEditing?.locationId,
                cityId: sofkianoForm.cityId,
                address: sofkianoForm.address,
                neighborhood: sofkianoForm.neighborhood,
                additionalIndications: sofkianoForm.additionalIndications
            }
        }
        setSofkianoToSave(sofkianoToSave)
    }

  return ( <Fragment>
    <Form 
    form={form}
    labelCol={{ span: 6 }}
    wrapperCol={{ span: 16 }}
    layout="horizontal"
    style={{ maxWidth: 600 }}
    autoComplete="off"
    initialValues={sofkianoEditing}
    onValuesChange={onFieldsChange}
    onFinish={onFinishForm}
    >

        <Form.Item
            label="Nombre"
            name="name"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Input type="text" />
        </Form.Item>
        

        <Form.Item
            label="Apellidos"
            name="lastName"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Input />
        </Form.Item>

        <Form.Item
            label="Tipo Documento"
            name="documentTypeId"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Select placeholder="Seleccione tipo documento">
                {
                    doumentTypeList.map((documentType:DocumentType) => {
                        return  <Option key={documentType.id} value={documentType.id}>{`${documentType.acronym} - ${documentType.name}`}</Option>
                    })
                }
            </Select>
        </Form.Item>
        
        <Form.Item
            label="Numero Documento"
            name="documentNumber"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Input />
        </Form.Item>

        <Form.Item
            label="Cliente"
            name="customerId"
        >
            <Select placeholder="Seleccione el cliente">
                {
                customerList.map((customer:Customer) => {
                    return  <Option key={customer.id} value={customer.id}>{customer.name}</Option>
                })
                }
            </Select>
        </Form.Item>

        <Form.Item
            label="Fecha de ingreso"
            name="entryDateSofkiano"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
            >
            <DatePicker value={sofkianoEditing?.entryDate ? dayjs(sofkianoEditing.entryDate) : undefined} placeholder="Seleccione Fecha" />
        </Form.Item>



        <Divider orientation="center">
            Ubicación
        </Divider>

        <Form.Item
            label="Pais"
            name="countryId"
        >
            <Select onChange={getStatesByCountryId} placeholder="Seleccione el Pais">
            {
            countryList.map((country:Country) => {
                return  <Option key={country.id} value={country.id}>{country.name}</Option>
            })
            }
            </Select>
        </Form.Item>

        <Form.Item
            label="Estado"
            name="stateId"
        >
            <Select onChange={getCityByStateId} placeholder="Seleccione la ciudad">
            {
            stateList.map((state:State) => {
                return  <Option key={state.id} value={state.id}>{state.name}</Option>
            })
            }
            </Select>
        </Form.Item>

        <Form.Item
            label="Ciudad"
            name="cityId"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Select placeholder="Seleccione la ciudad">
            {
            cityList.map((city:City) => {
                
                return  <Option key={city.id} value={city.id}>{city.name}</Option>
            })
            }
            </Select>
        </Form.Item>

        <Form.Item
            label="Dirección"
            name="address"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Input placeholder="Cra 1a #2b-3"/>
        </Form.Item>
        <Form.Item
            label="Barrio"
            name="neighborhood"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Input placeholder="La Floresta"/>
        </Form.Item>
        <Form.Item
            label="Datos adicionales"
            name="additionalIndications"
        >
            <TextArea placeholder="Torre 1 Apto 202 Urbanización abc"/>
        </Form.Item>



        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button type="primary" htmlType="submit">
                Guardar
            </Button>
        </Form.Item>

    </Form>

  </Fragment>
  )
}
