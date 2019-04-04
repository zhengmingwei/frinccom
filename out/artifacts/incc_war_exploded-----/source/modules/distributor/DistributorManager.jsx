import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshDistributorList} from 'actions/DistributorActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import DistributorList from './DistributorList';
import DistributorSearch from './DistributorSearch';

/**
 * Distributor: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class DistributorManager extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(refreshDistributorList());
    }

    view(id){
        this.history.push('/manager/distributor/detail/'+ id);
    }

    edit(id){
        this.history.push('/manager/distributor/form/'+ id);
    }

    delete(id){
        this.props.dispatch(refreshDistributorList(id));
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/分销商信息查询">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <DistributorSearch {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <DistributorList {...this.props}  onEdit={this.edit} onView={this.view} onDelete={this.delete}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {distributorList, distributorStatus, distributorCriteria} = state;
        return {
            distributorList, distributorStatus, distributorCriteria
        }
    }
)(DistributorManager);