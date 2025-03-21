import React from "react";
import { Row, Col} from "reactstrap";

import Widget from "../../components/Widget";


import s from "./Dashboard.module.scss";


import peopleA2 from "../../assets/people/a2.jpg";


class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      graph: null,
      checkedArr: [false, false, false],
    };
    this.checkTable = this.checkTable.bind(this);
  }

  checkTable(id) {
    let arr = [];
    if (id === 0) {
      const val = !this.state.checkedArr[0];
      for (let i = 0; i < this.state.checkedArr.length; i += 1) {
        arr[i] = val;
      }
    } else {
      arr = this.state.checkedArr;
      arr[id] = !arr[id];
    }
    if (arr[0]) {
      let count = 1;
      for (let i = 1; i < arr.length; i += 1) {
        if (arr[i]) {
          count += 1;
        }
      }
      if (count !== arr.length) {
        arr[0] = !arr[0];
      }
    }
    this.setState({
      checkedArr: arr,
    });
  }

  render() {
    return (
      <div className={s.root}>
        <h1 className="page-title">
          Produits &nbsp;
        </h1>

        <Row>
          <Col lg={6} xs={12}>
            <Widget
              title={
                <h6>
                Titre
                </h6>
              }
            >
              <div className="widget-body undo_padding">
                <div className="list-group list-group-lg">
                  <button className="list-group-item text-left">
                    <span className="float-left mr">
                      <img
                        src={peopleA2}
                        alt="..."
                      />
                      <i className="status status-bottom bg-success" />
                    </span>
                    <div>
                      <h6 className="m-0">Chris Gray</h6>
                      <p className="help-block text-ellipsis m-0">
                        Hey! What&apos;s up? So many times since we
                      </p>
                    </div>
                  </button>
                </div>
              </div>
              
            </Widget>
          </Col>
          <Col lg={6} xs={12}>
            <Widget
              title={
                <h6>
                Desscription
                </h6>
              }
            >
              <div className="widget-body undo_padding">
                <div className="list-group list-group-lg">
                  <button className="list-group-item text-left">
                    <span className="float-left mr">
                      <img
                        src={peopleA2}
                        alt="..."
                      />
                      <i className="status status-bottom bg-success" />
                    </span>
                    <div>
                      <h6 className="m-0">Chris Gray</h6>
                      <p className="help-block text-ellipsis m-0">
                        Hey! What&apos;s up? So many times since we
                      </p>
                    </div>
                  </button>
                </div>
              </div>
              
            </Widget>
          </Col>
          <Col lg={6} xs={12}>
            <Widget
              title={
                <h6>
                 Desscription
                </h6>
              }
            >
              <div className="widget-body undo_padding">
                <div className="list-group list-group-lg">
                  <button className="list-group-item text-left">
                    <span className="float-left mr">
                      <img
                        src={peopleA2}
                        alt="..."
                      />
                      <i className="status status-bottom bg-success" />
                    </span>
                    <div>
                      <h6 className="m-0">Chris Gray</h6>
                      <p className="help-block text-ellipsis m-0">
                        Hey! What&apos;s up? So many times since we
                      </p>
                    </div>
                  </button>
                </div>
              </div>
              
            </Widget>
          </Col>
          <Col lg={6} xs={12}>
            <Widget
              title={
                <h6>
                Desscription
                </h6>
              }
            >
              <div className="widget-body undo_padding">
                <div className="list-group list-group-lg">
                  <button className="list-group-item text-left">
                    <span className="float-left mr">
                      <img
                        src={peopleA2}
                        alt="..."
                      />
                      <i className="status status-bottom bg-success" />
                    </span>
                    <div>
                      <h6 className="m-0">Chris Gray</h6>
                      <p className="help-block text-ellipsis m-0">
                        Hey! What&apos;s up? So many times since we
                      </p>
                    </div>
                  </button>
                </div>
              </div>
              
            </Widget>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Dashboard;
