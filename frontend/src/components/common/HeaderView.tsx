import Layout, { Header } from 'antd/es/layout/layout'
import { Logo } from './Logo'

export const HeaderView = () => {
  return (
    <Layout>
      <Header>
      <Logo/>
    </Header>
    </Layout>
  )
}
