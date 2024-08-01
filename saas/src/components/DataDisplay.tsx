import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { fetchData } from '../services/api';
import '../CSS/DataDisplay.css';

interface DataItem {
  id: number;
  fileName: string;
  fileType: string;
  LocalDateTime: number;
}

const DataDisplay: React.FC = () => {
  const [data, setData] = useState<DataItem[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const getData = async () => {
      try {
        const fetchedData = await fetchData();
        setData(fetchedData);
        setLoading(false);
      } catch (error) {
        if (error instanceof Error) {
          setError(error.message);
        } else {
          setError('An unknown error occurred');
        }
        setLoading(false);
      }
    };
    getData();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }
 

  return (
    <div className="container mt-4">
      <Table bordered hover>
        <thead>
          <tr>
            <th>Sl</th>
            <th>Name</th>
            <th>Type</th>
            <th>Date Uploaded</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={item.id}>
              <td>{index + 1}</td>
              <td>{item.fileName}</td>
              <td>{item.fileType}</td>
              <td>{item.LocalDateTime}</td>
              <td>
                <Button variant="primary">Open</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default DataDisplay;
