import { Button, DatePicker, Divider, Form, Input, Row, Select } from "antd";
import { Fragment } from "react";
import { useListSofkiano } from "../hooks/useListSofkiano";
import { DocumentType } from "../../../types/DocumentType";
import { Customer } from "../../../types/Customer";
import { Country } from "../../../types/Country";
import { State } from "../../../types/State";
import { City } from "../../../types/City";
import { Sofkiano } from "../../../types/Sofkiano";

const { Option } = Select;
const {TextArea} = Input

export const CreateSofkianoPage = () => {

    const {
        doumentTypeList,
        customerList,
        countryList,
        stateList,
        getStatesByCountryId,
        cityList,
        getCityByStateId,
        sofkianoEditing
      } = useListSofkiano();

    function onFinishForm(values: Sofkiano): void {
        console.log(values)
    }

  return ( <Fragment>
    <Form 
    labelCol={{ span: 6 }}
    wrapperCol={{ span: 16 }}
    layout="horizontal"
    style={{ maxWidth: 600 }}
    autoComplete="off"
    initialValues={sofkianoEditing}
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
            name="customerName"
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
            name="entryDate"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
            >
            <DatePicker placeholder="Seleccione Fecha" />
        </Form.Item>



        <Divider orientation="center">
            Ubicación
        </Divider>

        <Form.Item
            label="Pais"
            name="countryName"
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
            name="stateName"
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
            name="cityName"
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
