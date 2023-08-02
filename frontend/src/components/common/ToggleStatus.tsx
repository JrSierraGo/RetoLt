
import { Switch } from "antd"
import { useState } from "react"

export const ToggleStatus = (checked:boolean,handleChangeStatus:Promise<boolean>) => {

    const [currentValue, setCUrrentValue] = useState(checked)

    const onChangeStatus = (_: boolean) => {
        handleChangeStatus
        .then(response => {
            if(response && response === true){
                setCUrrentValue(checked)
            }else{
                setCUrrentValue(!checked)
            }
        })
        .catch(err => {
            setCUrrentValue(!checked)
            console.error(err)
        })
    }

    return (
        <Switch checked={currentValue} onChange={(value) => onChangeStatus(value)} />
    )
  }