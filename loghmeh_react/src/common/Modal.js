import React, {Component, useState} from 'react';
import {Modal, Button} from 'react-bootstrap';
import Navbar from './Navbar';

function Example(props) {
    const [show, setShow] = useState(false);
  
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
      <div>
        <Button variant="primary" onClick={handleShow}>
          Launch demo modal
        </Button>
        <Modal show={show} onHide={handleClose} dialogClassName="modal-90w-100h">
          {/* <Modal.Header closeButton>
            <Modal.Title>Modal heading</Modal.Title>
          </Modal.Header> */}
          {/* <Modal.Body> */}
          {props.test}
          {/* </Modal.Body> */}
          {/* <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" onClick={handleClose}>
              Save Changes
            </Button>
          </Modal.Footer> */}
        </Modal>
        </div>
    );
  }
export default Example;