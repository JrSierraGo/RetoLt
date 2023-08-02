import { useEffect, useState } from "react";
import SofkianoService from "../../../services/api/SofkianoService";
import { ColumnsType } from "antd/es/table";
import { Button, Switch } from "antd";
import { Sofkiano } from "../../../types/Sofkiano";
import { useNavigate } from "react-router-dom";
import { EditOutlined } from "@ant-design/icons";

export const useListSofkiano = () => {

  const navigate = useNavigate();


  const [sofkianoList, setSofkianoList] = useState<Sofkiano[]>([]);
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
      render: (statusParam:string, record) => <Switch checked={record.status === 'ACTIVE' ? true : false} onChange={(value) => onChangeStatus(value, record, statusParam)} />
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

  const changeSofkianoStatus = async (sofkianoId:string, status:string, entryDate?:number): Promise<boolean> => {
    let changed:boolean = false;
    return await SofkianoService.saveStatusChange({sofkianoId, status, entryDate})
    .then(response => {
      if (response){
        changed = true
      }
      return changed
    })
    .catch(error => {
      console.log(error)
      return false;
    });

  }

  const onChangeStatus = async (value:boolean, record:any, statusParam: string) => {
    const status = value ? 'ACTIVE' : 'INACTIVE'
    let index = sofkianoList.indexOf(record);
    console.log(index)
    const response = await changeSofkianoStatus(record.id, status)
    if(!response){
      value = !value
      sofkianoList[index].status = 'INACTIVE'
    }
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