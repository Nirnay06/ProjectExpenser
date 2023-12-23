import { Button, Col, ConfigProvider, Row, Steps, message } from "antd";
import { useState } from "react";
import ImportStep1 from "./ImportSteps/ImportStep1";
import ImportStep4 from "./ImportSteps/ImportStep4";
import ImportStep3 from "./ImportSteps/ImportStep3";
import ImportStep2 from "./ImportSteps/ImportStep2";
import {useContext} from "react";
import RecordImportContext from "../../store/RecordImportContext";
import useServices from "../../hooks/useSevices";

const FileProcessSidebar = ({toggleFlow}) => {
  
  const RecordImportCtx = useContext(RecordImportContext);
  const {FileService} = useServices();
  const importRecordsFromUploadedFile = () => {
     FileService.importRecordsFromUploadedFile(RecordImportCtx.fileMetaData);
  };
  const steps = [
    {
      content: <ImportStep1/>,
    },
    {
      content:  <ImportStep2/>,
    },
    {
      content:  <ImportStep3/>,
    },
    {
      content:  <ImportStep4/>,
    },
  ];
  const next = () => {
    RecordImportCtx.setCurrentStep(RecordImportCtx.currentStep + 1);
  };
  const prev = () => {
    RecordImportCtx.setCurrentStep(RecordImportCtx.currentStep - 1);
  };
  const items = steps.map((item) => ({
    key: item.title,
    title: item.title,
  }));
  return (
    <>
      <span><p className="heading--3 font14 center">New import to account</p>
      <p className="heading--2 font16 center">PNB</p></span>
      <div> 
        <Steps style={{ marginLeft: "-1rem" }} className="custom-steps" type="navigation" current={RecordImportCtx.currentStep} items={items} labelPlacement="vertical" />
        <div>{steps[RecordImportCtx.currentStep].content}</div>
        <Row className="offsetT20" gutter={16}>
          <Col span={12}>
            {RecordImportCtx.currentStep > 0 && (
              <Button block shape="round" onClick={() => prev()}>
                Previous
              </Button>
            )}
          </Col>

          {RecordImportCtx.currentStep < steps.length - 1 && (
            <Col span={12}>
              <Button block type="primary" shape="round" onClick={() => next()}>
                Next
              </Button>
            </Col>
          )}
          {RecordImportCtx.currentStep === steps.length - 1 && (
            <Col span={12}>
              <Button block type="primary" shape="round" onClick={importRecordsFromUploadedFile}>
                Done
              </Button>
            </Col>
          )}
        </Row>
        
        <Button  className="offsetT20" block type="default" danger shape="round" onClick={() =>toggleFlow('review')}>
                Cancel
              </Button>
      </div>
    </>
  );
};

export default FileProcessSidebar;
