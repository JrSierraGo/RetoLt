import { Header } from 'antd/es/layout/layout'
import { Logo } from './Logo'
import { Affix } from 'antd'

export const HeaderView = () => {
  return (
    <Affix>
      <Header>
      <Logo/>
    </Header>
    </Affix>
  )
}
