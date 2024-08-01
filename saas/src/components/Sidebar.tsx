import React from 'react';
import { Nav } from 'react-bootstrap';
import '../CSS/Sidebar.css';


const Sidebar: React.FC = () => {
  return (
    <div className="sidebar d-flex flex-column p-3 text-white ">
      <div className="sidebar-header d-flex align-items-center mb-4">
        {/* <img
          //src="https://www.google.com/url?sa=i&url=https%3A%2F%2Fdatavizcatalogue.com%2Fmethods%2Farea_graph.html&psig=AOvVaw2aqPaQp4MtW8VX_izmZi1B&ust=1722500646208000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCMiSjYzt0IcDFQAAAAAdAAAAABAE" // Replace with your logo
          alt="Logo"
          className="me-2"
        /> */}
        <span className="fs-4">INSIGHT</span>
      </div>
      <Nav className="flex-column">
        <Nav.Link href="#" className="text-white">
          <i className="bi bi-grid-fill me-2"></i> Overview
        </Nav.Link>
        <Nav.Link href="#" className="text-white">
          <i className="bi bi-bar-chart-fill me-2"></i> Reports
        </Nav.Link>
        <Nav.Link href="#" className="text-white">
          <i className="bi bi-gear-fill me-2"></i> Settings
        </Nav.Link>
        <Nav.Link href="#" className="text-white">
          <i className="bi bi-wrench me-2"></i> Developer
        </Nav.Link>
      </Nav>
      <div className="mt-auto">
        <Nav.Link href="#" className="text-white">
          <i className="bi bi-box-arrow-right me-2"></i> Log out
        </Nav.Link>
      </div>
    </div>
  );
};

export default Sidebar;
