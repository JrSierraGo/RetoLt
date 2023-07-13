import { Button, Col, DatePicker, Form, Input, Row, Select } from "antd";
import { Fragment } from "react";
import { useLocation } from "react-router-dom";

const { Option } = Select;

export const CreateSofkianoPage = () => {

    const {state} = useLocation();
    const {sofkiano} = state;

    console.log(sofkiano)

  return ( <Fragment>
    <Form 
    labelCol={{ span: 6 }}
    wrapperCol={{ span: 16 }}
    layout="horizontal"
    style={{ maxWidth: 600 }}
    autoComplete="off"
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
            name="documentType"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Select placeholder="Seleccione tipo documento">
                <Option value="male">CC</Option>
                <Option value="female">TI</Option>
                <Option value="other">PE</Option>
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
            name="customer"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
        >
            <Select placeholder="Seleccione el cliente">
                <Option value="male">Compensar</Option>
                <Option value="female">Sura</Option>
            </Select>
        </Form.Item>

        <Form.Item
            label="Fecha de ingreso"
            name="entryDate"
            rules={[{ required: true, message: 'Este dato es requerido' }]}
            >
            <DatePicker placeholder="Seleccione Fecha" />
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
