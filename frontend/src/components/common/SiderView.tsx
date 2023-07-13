import { useNavigate } from "react-router-dom";
import Menu from "antd/es/menu/menu";
import { Layout } from "antd";
import { routes } from "../../routes";

const { Sider } = Layout;
export const SiderView = () => {
  const navigate = useNavigate();

  const getItemMenu = (
    label: any,
    key: any,
    icon: any,
    disabled: any,
    onClick: any
  ) => {
    return {
      label,
      key,
      icon,
      disabled,
      onClick,
    };
  };

  const itemList = routes.map((item: any) =>
    getItemMenu(item.label, item.key, item.icon, item.disabled, () => {
      return navigate(item.path);
    })
  );

  const siderStyle: React.CSSProperties = {
    textAlign: 'center',
    lineHeight: '120px'
  };

  
  return (
    <Sider>
      <Menu
        mode="inline"
        style={{
          height: "100%",
        }}
        items={itemList}
      />
   </Sider>
  );
};
