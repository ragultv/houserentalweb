import React, { useState, useEffect } from 'react';
import axios from 'axios';

function TenantList() {
  const [tenants, setTenants] = useState([]);
  const [newTenant, setNewTenant] = useState({ id: '', name: '', contact: '', preferredLocation: '' });
  const [matches, setMatches] = useState({});

  useEffect(() => {
    fetchTenants();
  }, []);

  const fetchTenants = async () => {
    const response = await axios.get('/tenants');
    setTenants(response.data);
  };

  const addTenant = async (e) => {
    e.preventDefault();
    await axios.post('/tenants', newTenant);
    setNewTenant({ id: '', name: '', contact: '', preferredLocation: '' });
    fetchTenants();
  };

  const showMatches = async (id) => {
    const response = await axios.get(`/tenants/${id}/matches`);
    setMatches(prev => ({ ...prev, [id]: response.data }));
  };

  return (
    <div>
      <h2>Tenants</h2>
      <form onSubmit={addTenant}>
        <input placeholder="ID" value={newTenant.id} onChange={(e) => setNewTenant({ ...newTenant, id: e.target.value })} />
        <input placeholder="Name" value={newTenant.name} onChange={(e) => setNewTenant({ ...newTenant, name: e.target.value })} />
        <input placeholder="Contact" value={newTenant.contact} onChange={(e) => setNewTenant({ ...newTenant, contact: e.target.value })} />
        <input placeholder="Preferred Location" value={newTenant.preferredLocation} onChange={(e) => setNewTenant({ ...newTenant, preferredLocation: e.target.value })} />
        <button type="submit">Register Tenant</button>
      </form>
      <ul>
        {tenants.map(tenant => (
          <li key={tenant.id}>
            {tenant.id} - {tenant.name} - {tenant.contact} - Prefers: {tenant.preferredLocation}
            <button onClick={() => showMatches(tenant.id)}>Show Matches</button>
            {matches[tenant.id] && (
              <ul>
                {matches[tenant.id].map(house => (
                  <li key={house.id}>{house.id} - {house.location}</li>
                ))}
              </ul>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TenantList;