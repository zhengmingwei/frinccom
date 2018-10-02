import React from 'react';
import ReactDOM from 'react-dom';

/**
 * User: zyj
 * Date: 16/7/8.
 * Time: 下午3:42.
 */
export default class FileList extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {fileInfoList=[]} = this.props;
        let rows = [];
        if (fileInfoList) fileInfoList.map((fileInfo,index)=>{if (fileInfo)rows.push(
            <div key = {index} className="ant-upload-list-item ant-upload-list-item-done">
                <div className="ant-upload-list-item-info"><span><i className="anticon anticon-paper-clip"></i>
                    <a href={fileInfo.url} target="_blank" rel="noopener noreferrer" className="ant-upload-list-item-name" title={fileInfo.name}>{fileInfo.name}</a>
                </span></div>
            </div>
        )})
        return (
            <div className="ant-upload-list ant-upload-list-text">
                {rows}
            </div>

        )
    }
};

