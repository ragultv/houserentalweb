import React, { useState, useEffect } from 'react';
import axios from 'axios';

function HouseList() {
  const [houses, setHouses] = useState([]);
  const [newHouse, setNewHouse] = useState({ id: '', location: '', price: '', bedrooms: '', owner: '' });
  const [search, setSearch] = useState({ location: '', maxPrice: '' });

  useEffect(() => {
    fetchHouses();
  }, []);

  const fetchHouses = async () => {
    const response = await axios.get('/houses');
    setHouses(response.data);
  };

  const addHouse = async (e) => {
    e.preventDefault();
    await axios.post('/houses', { ...newHouse, price: parseFloat(newHouse.price), bedrooms: parseInt(newHouse.bedrooms) });
    setNewHouse({ id: '', location: '', price: '', bedrooms: '', owner: '' });
    fetchHouses();
  };

  const removeHouse = async (id) => {
    await axios.delete(`/houses/${id}`);
    fetchHouses();
  };

  const searchHouses = async (e) => {
    e.preventDefault();
    const response = await axios.get(`/houses/search?location=${search.location}&maxPrice=${search.maxPrice}`);
    setHouses(response.data);
  };

  return (
    <div>
      <h2>Houses</h2>
      <form onSubmit={addHouse}>
        <input placeholder="ID" value={newHouse.id} onChange={(e) => setNewHouse({ ...newHouse, id: e.target.value })} />
        <input placeholder="Location" value={newHouse.location} onChange={(e) => setNewHouse({ ...newHouse, location: e.target.value })} />
        <input placeholder="Price" type="number" value={newHouse.price} onChange={(e) => setNewHouse({ ...newHouse, price: e.target.value })} />
        <input placeholder="Bedrooms" type="number" value={newHouse.bedrooms} onChange={(e) => setNewHouse({ ...newHouse, bedrooms: e.target.value })} />
        <input placeholder="Owner" value={newHouse.owner} onChange={(e) => setNewHouse({ ...newHouse, owner: e.target.value })} />
        <button type="submit">Add House</button>
      </form>
      <form onSubmit={searchHouses}>
        <input placeholder="Location" value={search.location} onChange={(e) => setSearch({ ...search, location: e.target.value })} />
        <input placeholder="Max Price" type="number" value={search.maxPrice} onChange={(e) => setSearch({ ...search, maxPrice: e.target.value })} />
        <button type="submit">Search</button>
        <button type="button" onClick={fetchHouses}>Reset</button>
      </form>
      <ul>
        {houses.map(house => (
          <li key={house.id}>
            {house.id} - {house.location} - ${house.price} - {house.bedrooms} beds - {house.owner} - 
            {house.isBooked ? 'Booked' : 'Available'}
            {!house.isBooked && <button onClick={() => removeHouse(house.id)}>Remove</button>}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default HouseList;