import { Image } from 'antd'
import logo from "../../assets/logo-sofka-blanco.png";

export const Logo = () => {
  return (
    <Image
    preview={false}
    width={150}
    src={logo}
  />
  )
}
