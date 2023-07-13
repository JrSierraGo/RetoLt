import { HeaderView } from "./common/HeaderView";
import { SiderView } from "./common/SiderView";
import { Outlet } from "react-router-dom";
import { Layout } from "antd";
import { Content, Footer } from "antd/es/layout/layout";
export const MainLayout = () => {

  const contentStyle: React.CSSProperties = {
    textAlign: 'center',
    minHeight: '80vh',
    padding: '3vw'
  };


  return (
    <Layout style={{minHeight:'100vh'}}>
      <HeaderView/>
      <Layout hasSider>
        <SiderView />
        <Content style={contentStyle}>
          <Outlet />
        </Content>
      </Layout>
      <Footer
        style={{
          textAlign: "center",
          background: '#001529',
          color: 'white'
        }}
      >
        ©Sofka 2023.- Todos los derechos reservados
      </Footer>
    </Layout>
  );
};
