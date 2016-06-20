<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New User Chat'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="userChat index large-9 medium-8 columns content">
    <h3><?= __('User Chat') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('chatID') ?></th>
                <th><?= $this->Paginator->sort('userID1') ?></th>
                <th><?= $this->Paginator->sort('userID2') ?></th>
                <th><?= $this->Paginator->sort('message') ?></th>
                <th><?= $this->Paginator->sort('fromUser') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th><?= $this->Paginator->sort('isBlock') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($userChat as $userChat): ?>
            <tr>
                <td><?= $this->Number->format($userChat->chatID) ?></td>
                <td><?= $this->Number->format($userChat->userID1) ?></td>
                <td><?= $this->Number->format($userChat->userID2) ?></td>
                <td><?= h($userChat->message) ?></td>
                <td><?= $this->Number->format($userChat->fromUser) ?></td>
                <td><?= h($userChat->createDate) ?></td>
                <td><?= h($userChat->updateDate) ?></td>
                <td><?= h($userChat->delFlg) ?></td>
                <td><?= $this->Number->format($userChat->isBlock) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $userChat->chatID]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $userChat->chatID]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $userChat->chatID], ['confirm' => __('Are you sure you want to delete # {0}?', $userChat->chatID)]) ?>
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
