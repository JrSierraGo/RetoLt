import { useEffect, useState } from "react";
import SofkianoService from "../../../services/api/SofkianoService";
import { ColumnsType } from "antd/es/table";
import { Button, Switch, message } from "antd";
import { Sofkiano } from "../../../types/Sofkiano";
import { useNavigate } from "react-router-dom";
import { EditOutlined } from "@ant-design/icons";

export const useCreateSofkiano = () => {

  const navigate = useNavigate();


  const [sofkianoList, setSofkianoList] = useState([]);
  const [isChangedStatus, setIsChangedStatus] = useState(false);
  const [pagination, setPagination] = useState({
    current: 0,
    size: 10,
    total: 0
  });

  const columns: ColumnsType<Sofkiano> = [
    {
      title: 'Nombres',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Apellidos',
      dataIndex: 'lastName',
      key: 'lastName',
    },
    {
      title: 'Tipo Documento',
      dataIndex: 'documentTypeName',
      key: 'documentTypeName',
    },
    {
      title: 'Numero Documento',
      dataIndex: 'documentNumber',
      key: 'documentNumber',
    },
    {
      title: 'Cliente',
      dataIndex: 'customerName',
      key: 'customerName',
    },
    {
      title: 'Estado',
      dataIndex: 'status',
      key: 'status',
      render: (statusParam, record) => <Switch defaultChecked={statusParam === 'ACTIVE' ? true : false} onChange={(value) => onChangeStatus(value, record)} />
    },
    {
      title: 'Accion',
      dataIndex: 'action',
      key: 'action',
      render: (param, record) => <Button shape="round" icon={<EditOutlined />}  onClick={(e) => goToEditSofkiano(record)}></Button>
    }
  ]

  useEffect(() => {

    const getSofkianosList = () => {
      SofkianoService.getSofkianosList(pagination.current, pagination.size)
      .then(response => {
        if (response) {
          setSofkianoList(response.sofkianos)
          setPagination({
            ...pagination, 
            total: response.totalPages
          })
          }
      })
      .catch(error => console.log(error));
    }

    getSofkianosList();
  }, []);

  const changeSofkianoStatus = (sofkianoId:string, status:string, entryDate?:number) : any => {
    return SofkianoService.saveStatusChange({sofkianoId, status, entryDate})
    .then(response => {
      console.log(response)
    })
    .catch(error => console.log(error));
  }

  const onChangeStatus = async (checked: boolean, record:any) => {
    const status = checked ? 'ACTIVE' : 'INACTIVE'
    try {
      await changeSofkianoStatus(record.id, status)
      if (isChangedStatus) {
        message.success("Se cambió el estado con exito")
      }else{
        message.error("Algo salió mal intentalo nuevamente")
      }
    } catch (error) {
      message.error("Algo salió mal intentalo nuevamente")
    }
    setIsChangedStatus(false)
  }

  const goToCreateSofkiano = () => {
    navigate("/sofkianos/crear", {state:{sofkiano:null}})
  }

  const goToEditSofkiano = (sofkiano:Sofkiano) => {
    navigate(`/sofkianos/editar/${sofkiano.id}`, {state: {sofkiano}})
  }

  return {
    sofkianoList,
    columns,
    goToCreateSofkiano
  };
};