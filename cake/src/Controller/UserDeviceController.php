<?php
namespace App\Controller;

use App\Controller\AppController;

/**
 * UserDevice Controller
 *
 * @property \App\Model\Table\UserDeviceTable $UserDevice
 */
class UserDeviceController extends AppController
{

    /**
     * Index method
     *
     * @return \Cake\Network\Response|null
     */
    public function index()
    {
        $userDevice = $this->paginate($this->UserDevice);

        $this->set(compact('userDevice'));
        $this->set('_serialize', ['userDevice']);
    }

    /**
     * View method
     *
     * @param string|null $id User Device id.
     * @return \Cake\Network\Response|null
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $userDevice = $this->UserDevice->get($id, [
            'contain' => []
        ]);

        $this->set('userDevice', $userDevice);
        $this->set('_serialize', ['userDevice']);
    }

    /**
     * Add method
     *
     * @return \Cake\Network\Response|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        $userDevice = $this->UserDevice->newEntity();
        if ($this->request->is('post')) {
            $userDevice = $this->UserDevice->patchEntity($userDevice, $this->request->data);
            if ($this->UserDevice->save($userDevice)) {
                $this->Flash->success(__('The user device has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The user device could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('userDevice'));
        $this->set('_serialize', ['userDevice']);
    }

    /**
     * Edit method
     *
     * @param string|null $id User Device id.
     * @return \Cake\Network\Response|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Network\Exception\NotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $userDevice = $this->UserDevice->get($id, [
            'contain' => []
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $userDevice = $this->UserDevice->patchEntity($userDevice, $this->request->data);
            if ($this->UserDevice->save($userDevice)) {
                $this->Flash->success(__('The user device has been saved.'));
                return $this->redirect(['action' => 'index']);
            } else {
                $this->Flash->error(__('The user device could not be saved. Please, try again.'));
            }
        }
        $this->set(compact('userDevice'));
        $this->set('_serialize', ['userDevice']);
    }

    /**
     * Delete method
     *
     * @param string|null $id User Device id.
     * @return \Cake\Network\Response|null Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $userDevice = $this->UserDevice->get($id);
        if ($this->UserDevice->delete($userDevice)) {
            $this->Flash->success(__('The user device has been deleted.'));
        } else {
            $this->Flash->error(__('The user device could not be deleted. Please, try again.'));
        }
        return $this->redirect(['action' => 'index']);
    }
}
