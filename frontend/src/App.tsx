import React from "react";
import { MainLayout } from "./components/MainLayout";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SofkianoListPage from "./pages/sofkiano/components/SofkianoListPage";
import LoadUsers from "./pages/loadUsers/components/LoadUsers";
import { Stats } from "./pages/stats/components/Stats";
import { CreateSofkianoPage } from "./pages/sofkiano/components/CreateSofkianoPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route path="/sofkianos" element={<SofkianoListPage />} />
          <Route path="/sofkianos/crear" element={<CreateSofkianoPage />} />
          <Route path="/sofkianos/editar/:id" element={<CreateSofkianoPage />} />
          <Route path="/carga-masiva" element={<LoadUsers />} />
          <Route path="/estadisticas" element={<Stats />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
