import { HeaderView } from "./common/HeaderView";
import { SiderView } from "./common/SiderView";
import { Outlet } from "react-router-dom";
import { Layout } from "antd";
import { Content, Footer } from "antd/es/layout/layout";
export const MainLayout = () => {
  return (
    <Layout style={{minHeight:'100vh'}}>
      <HeaderView/>
      <Layout hasSider>
        <SiderView/>
        <Content>
          <Outlet />
        </Content>
      </Layout>
      <Footer
        style={{
          textAlign: "center",
        }}
      >
        ©Sofka 2023.- Todos los derechos reservados
      </Footer>
    </Layout>
  );
};
