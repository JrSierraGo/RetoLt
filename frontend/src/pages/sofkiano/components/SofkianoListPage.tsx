import Table from "antd/es/table";
import { useCreateSofkiano } from "../hooks/useCreateSofkiano";
import { Fragment } from "react";
import { Button } from "antd";

const SofkianoListPage = () => {

  const {
    sofkianoList,
    columns,
    goToCreateSofkiano
  } = useCreateSofkiano();

  return (
  <Fragment>
    <Button type="primary" onClick={goToCreateSofkiano}>Crear Sofkiano</Button>
    <Table
      rowKey={(record) => record.id}
      columns={columns}
      dataSource={sofkianoList}
    />
  </Fragment>
  )
}

export default SofkianoListPage