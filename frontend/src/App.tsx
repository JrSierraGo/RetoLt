import React from "react";
import { MainLayout } from "./components/MainLayout";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Users from "./pages/users/components/Users";
import LoadUsers from "./pages/loadUsers/components/LoadUsers";
import { Stats } from "./pages/stats/components/Stats";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route path="/sofkianos" element={<Users />} />
          <Route path="/carga-masiva" element={<LoadUsers />} />
          <Route path="/estadisticas" element={<Stats />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
