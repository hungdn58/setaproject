<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New User Device'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="userDevice index large-9 medium-8 columns content">
    <h3><?= __('User Device') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('userID') ?></th>
                <th><?= $this->Paginator->sort('deviceID') ?></th>
                <th><?= $this->Paginator->sort('deviceToken') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($userDevice as $userDevice): ?>
            <tr>
                <td><?= $this->Number->format($userDevice->userID) ?></td>
                <td><?= h($userDevice->deviceID) ?></td>
                <td><?= h($userDevice->deviceToken) ?></td>
                <td><?= h($userDevice->createDate) ?></td>
                <td><?= h($userDevice->updateDate) ?></td>
                <td><?= $this->Number->format($userDevice->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $userDevice->userID]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $userDevice->userID]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $userDevice->userID], ['confirm' => __('Are you sure you want to delete # {0}?', $userDevice->userID)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
