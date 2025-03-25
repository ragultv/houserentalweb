import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AgreementList() {
  const [agreements, setAgreements] = useState([]);
  const [booking, setBooking] = useState({ houseId: '', tenantId: '', startDate: '', endDate: '', deposit: '' });
  const [payment, setPayment] = useState({});
  const [houses, setHouses] = useState([]);
  const [tenants, setTenants] = useState([]);

  useEffect(() => {
    fetchAgreements();
    fetchHouses();
    fetchTenants();
  }, []);

  const fetchAgreements = async () => {
    const response = await axios.get('/agreements');
    setAgreements(response.data);
  };

  const fetchHouses = async () => {
    const response = await axios.get('/houses');
    setHouses(response.data);
  };

  const fetchTenants = async () => {
    const response = await axios.get('/tenants');
    setTenants(response.data);
  };

  const bookHouse = async (e) => {
    e.preventDefault();
    await axios.post('/agreements', booking);
    setBooking({ houseId: '', tenantId: '', startDate: '', endDate: '', deposit: '' });
    fetchAgreements();
  };

  const recordPayment = async (agreementId) => {
    const paymentData = payment[agreementId] || { date: '', amount: '' };
    await axios.post(`/agreements/${agreementId}/payments`, paymentData);
    setPayment(prev => ({ ...prev, [agreementId]: { date: '', amount: '' } }));
    fetchAgreements();
  };

  return (
    <div>
      <h2>Agreements</h2>
      <form onSubmit={bookHouse}>
        <select value={booking.houseId} onChange={(e) => setBooking({ ...booking, houseId: e.target.value })}>
          <option value="">Select House</option>
          {houses.filter(h => !h.isBooked).map(h => (
            <option key={h.id} value={h.id}>{h.id} - {h.location}</option>
          ))}
        </select>
        <select value={booking.tenantId} onChange={(e) => setBooking({ ...booking, tenantId: e.target.value })}>
          <option value="">Select Tenant</option>
          {tenants.map(t => (
            <option key={t.id} value={t.id}>{t.id} - {t.name}</option>
          ))}
        </select>
        <input type="date" value={booking.startDate} onChange={(e) => setBooking({ ...booking, startDate: e.target.value })} />
        <input type="date" value={booking.endDate} onChange={(e) => setBooking({ ...booking, endDate: e.target.value })} />
        <input type="number" placeholder="Deposit" value={booking.deposit} onChange={(e) => setBooking({ ...booking, deposit: e.target.value })} />
        <button type="submit">Book House</button>
      </form>
      <ul>
        {agreements.map(agreement => (
          <li key={agreement.id}>
            {agreement.id} - House: {agreement.houseId} - Tenant: {agreement.tenantId} - 
            Due: {agreement.nextDueDate} {agreement.isOverdue ? '(Overdue)' : ''}
            <div>
              <input type="date" value={(payment[agreement.id] || {}).date || ''} 
                onChange={(e) => setPayment({ ...payment, [agreement.id]: { ...payment[agreement.id], date: e.target.value } })} />
              <input type="number" placeholder="Amount" value={(payment[agreement.id] || {}).amount || ''} 
                onChange={(e) => setPayment({ ...payment, [agreement.id]: { ...payment[agreement.id], amount: e.target.value } })} />
              <button onClick={() => recordPayment(agreement.id)}>Record Payment</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AgreementList;