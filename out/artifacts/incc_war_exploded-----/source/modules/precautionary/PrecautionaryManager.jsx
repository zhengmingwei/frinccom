import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshPrecautionaryList, precautionaryCriteriaChange} from 'actions/PrecautionaryActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import PrecautionaryList from './PrecautionaryList';
import PrecautionarySearch from './PrecautionarySearch';

/**
 * Precautionary: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class PrecautionaryManager extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const statusId = this.props.params.status;
        this.props.dispatch(refreshPrecautionaryList({status:statusId}));
        this.props.dispatch(precautionaryCriteriaChange({status:statusId}));
    }

    shouldComponentUpdate (nextProps, nextState) {
        const statusId = nextProps.params.status;
        const oldStatusId = this.props.params.status;

        if (statusId !== oldStatusId){
            this.props.dispatch(refreshPrecautionaryList({status:statusId}));
            this.props.dispatch(precautionaryCriteriaChange({status:statusId}));
            return false;
        }
        return true;
    }

    render() {
        const status = this.props.params.status;
        return (
            <div key ={this.props.params.status}>
                <SubPage breadcrumb="/后台管理/标识到期提醒/所有授权的企业">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <PrecautionarySearch {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <PrecautionaryList {...this.props} onEdit={this.edit} onView={this.view}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {precautionaryList, precautionaryCriteria} = state;
        return {
            precautionaryList, precautionaryCriteria
        }
    }
)(PrecautionaryManager);