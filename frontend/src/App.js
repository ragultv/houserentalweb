import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import HouseList from './HouseList';
import TenantList from './TenantList';
import AgreementList from './AgreementList';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><Link to="/houses">Houses</Link></li>
            <li><Link to="/tenants">Tenants</Link></li>
            <li><Link to="/agreements">Agreements</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/houses" element={<HouseList />} />
          <Route path="/tenants" element={<TenantList />} />
          <Route path="/agreements" element={<AgreementList />} />
          <Route path="/" element={<h1>Welcome to House Rental Management</h1>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;